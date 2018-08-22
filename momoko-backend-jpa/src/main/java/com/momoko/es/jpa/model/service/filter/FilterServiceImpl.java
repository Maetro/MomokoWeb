package com.momoko.es.jpa.model.service.filter;

import com.momoko.es.api.dto.LibroDTO;
import com.momoko.es.api.dto.filter.FilterDTO;
import com.momoko.es.api.dto.filter.SaveFilterResponse;
import com.momoko.es.api.dto.genre.GenreDTO;
import com.momoko.es.api.enums.EstadoGuardadoEnum;
import com.momoko.es.api.enums.errores.FilterCreationError;
import com.momoko.es.api.service.FilterService;
import com.momoko.es.commons.security.UserAdminPermission;
import com.momoko.es.commons.security.UserEditPermission;
import com.momoko.es.jpa.model.entity.GenreEntity;
import com.momoko.es.jpa.model.entity.LibroEntity;
import com.momoko.es.jpa.model.entity.filter.FilterBook;
import com.momoko.es.jpa.model.entity.filter.FilterEntity;
import com.momoko.es.jpa.model.repository.GeneroRepository;
import com.momoko.es.jpa.model.repository.LibroRepository;
import com.momoko.es.jpa.model.repository.filter.FilterRepository;
import com.momoko.es.jpa.model.service.ValidadorService;
import com.momoko.es.jpa.model.util.ConversionUtils;
import com.momoko.es.jpa.model.util.DTOToEntityAdapter;
import com.momoko.es.jpa.model.util.EntityToDTOAdapter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired(required = false)
    private FilterRepository filterRepository;

    @Autowired(required = false)
    private ValidadorService validatorService;

    @Autowired(required = false)
    private GeneroRepository genreRepository;

    @Autowired(required = false)
    private LibroRepository libroRepository;


    @Override
    public List<FilterDTO> getAllFilters() {
        Set<FilterEntity> filterEntities = filterRepository.findAll();
        return EntityToDTOAdapter.adaptFilters(filterEntities);
    }

    @Override
    @UserAdminPermission
    public SaveFilterResponse saveFilter(FilterDTO filterDTO) throws Exception {
        SaveFilterResponse response = new SaveFilterResponse();
        // Validar
        final List<FilterCreationError> listaErrores = this.validatorService.validateFilter(filterDTO);

        // Guardar
        FilterDTO savedFilterDTO = null;
        if (CollectionUtils.isEmpty(listaErrores)) {
            try {
                if (filterDTO.getFilterId() != null){
                    savedFilterDTO = updateFilter(filterDTO);
                } else{
                    savedFilterDTO = createFilter(filterDTO);
                }
            } catch (final Exception e) {
                e.printStackTrace();
                listaErrores.add(FilterCreationError.UNKNOWN);
                response.setErrorMessage(e);
                response.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
            }
        }

        // Responder
        response.setFilterDTO(savedFilterDTO);
        response.setListaErroresValidacion(listaErrores);

        if ((savedFilterDTO != null) && CollectionUtils.isEmpty(listaErrores)) {
            response.setEstadoGuardado(EstadoGuardadoEnum.CORRECTO);
        } else {
            response.setEstadoGuardado(EstadoGuardadoEnum.ERROR);
        }
        return response;
    }

    private FilterDTO createFilter(FilterDTO filterDTO) {
        FilterEntity filterEntity = DTOToEntityAdapter.adaptFilter(filterDTO);
        setApplicableGenresToFilter(filterDTO, filterEntity);
        FilterEntity savedFilterEntity = filterRepository.save(filterEntity);
        return EntityToDTOAdapter.adaptFilter(savedFilterEntity);
    }

    private FilterDTO updateFilter(FilterDTO filterDTO) throws InstanceNotFoundException {
        FilterEntity oldFilter = filterRepository.findById(filterDTO.getFilterId()).orElseThrow(() ->
                new InstanceNotFoundException("No se encuentra el filtro con el id: " + filterDTO.getFilterId()));
        setApplicableGenresToFilter(filterDTO, oldFilter);
        if (CollectionUtils.isNotEmpty(filterDTO.getBooks())){
            List<String> urlsList = filterDTO.getBooks().stream().map(LibroDTO::getUrlLibro).collect(Collectors.toList());
            List<LibroEntity> books = this.libroRepository.findByUrlLibroIn(urlsList);
            List<FilterBook> filterBooks = new ArrayList<>();
            for (LibroEntity book : books) {
                filterBooks.add(new FilterBook(oldFilter, book));
            }
            oldFilter.setFilterBooks(filterBooks);
        }
        oldFilter.setNameFilter(filterDTO.getNameFilter());
        oldFilter.setUrlFilter(filterDTO.getUrlFilter());
        oldFilter.setPossibleValues(ConversionUtils.join(filterDTO.getPossibleValues()));
        FilterEntity updatedFilter = filterRepository.save(oldFilter);
        return EntityToDTOAdapter.adaptFilter(updatedFilter);
    }

    private void setApplicableGenresToFilter(FilterDTO filterDTO, FilterEntity filterEntity) {
        if (CollectionUtils.isNotEmpty(filterDTO.getGenres())){
            List<String> urlsList = filterDTO.getGenres().stream().map(GenreDTO::getUrlGenero).collect(Collectors.toList());
            List<GenreEntity> genresFilter = genreRepository.findByUrlGeneroIn(urlsList);
            filterEntity.setApplicableGenres(genresFilter);
        }
    }

    @Override
    public FilterDTO getFilter(String urlFilter) {
        return null;
    }

    @Override
    public FilterDTO getFilterById(Integer filterId) {
        return null;
    }

    @Override
    public FilterDTO getFilterByUrl(String urlFilter) {
        FilterEntity filterEntity = filterRepository.findOneByUrlFilterIs(urlFilter);
        return EntityToDTOAdapter.adaptFilter(filterEntity);
    }

    @Override
    public List<FilterDTO> getFiltersAppliedByGenreUrl(List<String> urlGenres) {
        List<GenreEntity> genres = this.genreRepository.findByUrlGeneroIn(urlGenres);
        Set<FilterEntity> filterGenre = this.filterRepository.findAllByApplicableGenresIn(genres);
        Set<FilterEntity> genericFilters = this.filterRepository.findAllByApplicableGenresIsNull();
        filterGenre.addAll(genericFilters);
        return EntityToDTOAdapter.adaptFilters(filterGenre);
    }
}
