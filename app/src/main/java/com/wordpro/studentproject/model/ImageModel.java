package com.wordpro.studentproject.model;

public class ImageModel {

    /**
     * name : Deadpool
     * url : {"small":"https://api.androidhive.info/images/glide/small/deadpool.jpg","medium":"https://api.androidhive.info/images/glide/medium/deadpool.jpg","large":"https://api.androidhive.info/images/glide/large/deadpool.jpg"}
     * timestamp : February 12, 2016
     */

    private String name;
    private UrlBean url;
    private String timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UrlBean getUrl() {
        return url;
    }

    public void setUrl(UrlBean url) {
        this.url = url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class UrlBean {
        /**
         * small : https://api.androidhive.info/images/glide/small/deadpool.jpg
         * medium : https://api.androidhive.info/images/glide/medium/deadpool.jpg
         * large : https://api.androidhive.info/images/glide/large/deadpool.jpg
         */

        private String small;
        private String medium;
        private String large;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }
    }
}
