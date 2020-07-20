package by.epam.corporate_education.service.util.impl;

import by.epam.corporate_education.service.util.api.PagesCounter;

public class PagesCounterImpl implements PagesCounter {
    private final static int RECORD_PER_PAGE = 6;

    @Override
    public int getPagesAmount(int rows) {
        int pagesAmount = 0;

        pagesAmount = rows / RECORD_PER_PAGE;
        if (pagesAmount % RECORD_PER_PAGE > 0){
            pagesAmount++;
        }

        return pagesAmount;
    }

    @Override
    public int getRECORDS_PER_PAGE() {
        return RECORD_PER_PAGE;
    }
}
