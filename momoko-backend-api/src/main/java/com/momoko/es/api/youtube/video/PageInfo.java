/**
 * PageInfo.java 18-nov-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */

package com.momoko.es.api.youtube.video;

/**
 * The Class PageInfo.
 */
public class PageInfo {

    /** The total results. */
    private Integer totalResults;

    /** The results per page. */
    private Integer resultsPerPage;

    /**
     * Gets the total results.
     *
     * @return the total results
     */
    public Integer getTotalResults() {
        return this.totalResults;
    }

    /**
     * Sets the total results.
     *
     * @param totalResults
     *            the new total results
     */
    public void setTotalResults(final Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Gets the results per page.
     *
     * @return the results per page
     */
    public Integer getResultsPerPage() {
        return this.resultsPerPage;
    }

    /**
     * Sets the results per page.
     *
     * @param resultsPerPage
     *            the new results per page
     */
    public void setResultsPerPage(final Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

}
