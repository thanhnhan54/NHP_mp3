package dto;

public class Pageable {
        private String search;
        private int page;
        private int totalItems;
        private int totalPage;

        private String nameField;

        private String sortBy;
    private int filterAuthor;
    private int filterSinger;
    private int filterCategory;

    public int getFilterAuthor() {
        return filterAuthor;
    }

    public void setFilterAuthor(int filterAuthor) {
        this.filterAuthor = filterAuthor;
    }

    public int getFilterSinger() {
        return filterSinger;
    }

    public void setFilterSinger(int filterSinger) {
        this.filterSinger = filterSinger;
    }

    public int getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(int filterCategory) {
        this.filterCategory = filterCategory;
    }

    public Pageable(String search, int page, int totalItems) {
            this.search = search;
            this.page = page;
            this.totalItems = totalItems;
        }

        public Pageable(String search, int page, int totalItems, String nameField, String sortBy) {
            this.search = search;
            this.page = page;
            this.totalItems = totalItems;
            this.nameField = nameField;
            this.sortBy = sortBy;
        }

    public Pageable(String search, int page, int totalItems, String nameField, int filterAuthor, int filterSinger, int filterCategory) {
        this.search = search;
        this.page = page;
        this.totalItems = totalItems;
        this.nameField = nameField;
        this.filterAuthor = filterAuthor;
        this.filterSinger = filterSinger;
        this.filterCategory = filterCategory;
    }

    public Pageable(String search, int page, int totalItems, String nameField, String sortBy, int filterAuthor, int filterSinger, int filterCategory) {
        this.search = search;
        this.page = page;
        this.totalItems = totalItems;
        this.nameField = nameField;
        this.sortBy = sortBy;
        this.filterAuthor = filterAuthor;
        this.filterSinger = filterSinger;
        this.filterCategory = filterCategory;
    }

    public Pageable() {
    }

    public Pageable(String search, int page, int totalItems, int totalPage) {
            this.search = search;
            this.page = page;
            this.totalItems = totalItems;
            this.totalPage = totalPage;
        }

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public String getNameField() {
            return nameField;
        }

        public void setNameField(String nameField) {
            this.nameField = nameField;
        }

        public String getSortBy() {
            return sortBy;
        }

        public void setSortBy(String sortBy) {
            this.sortBy = sortBy;
        }
}

