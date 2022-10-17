package com.wovenreviews.java.dto;


public class AuditsRequest {

    private AuditsQueue q;

    public AuditsQueue getQ() {
        return q;
    }

    public void setQ(AuditsQueue q) {
        this.q = q;
    }

    public static class AuditsQueue {

        private AuditsFilters filters;

        public AuditsFilters getFilters() {
            return filters;
        }

        public void setFilters(AuditsFilters filters) {
            this.filters = filters;
        }
    }

    public static class AuditsFilters {

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
