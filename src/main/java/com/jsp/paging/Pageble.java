package com.jsp.paging;

import com.jsp.sort.Sorter;

public interface Pageble {

        public Integer getCurrentPage();
        public Integer getOffSet();
        public Integer getMaxPageItem();
        public Sorter getSorter();
}
