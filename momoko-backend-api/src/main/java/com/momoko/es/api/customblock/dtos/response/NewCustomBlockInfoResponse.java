package com.momoko.es.api.customblock.dtos.response;

import com.momoko.es.api.customblock.dtos.LabelUrlTypeDTO;

import java.util.List;

public class NewCustomBlockInfoResponse {

    List<LabelUrlTypeDTO> booksAndEntries;

    public List<LabelUrlTypeDTO> getBooksAndEntries() {
        return booksAndEntries;
    }

    public void setBooksAndEntries(List<LabelUrlTypeDTO> booksAndEntries) {
        this.booksAndEntries = booksAndEntries;
    }
}
