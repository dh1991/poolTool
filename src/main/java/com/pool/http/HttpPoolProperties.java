package com.pool.http;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(
        prefix = "http.pooling"
)
public class HttpPoolProperties{
        private boolean isOpen;
        private Integer maxTotal = 20;
        private Integer maxPerRoute = 2;
        private Integer connectionRequestTimeout = 20000;
        private Integer connectTimeout = 20000;
        private Integer socketTimeout = 20000;

        public boolean isOpen() {
                return isOpen;
        }

        public void setOpen(boolean open) {
                isOpen = open;
        }

        public Integer getMaxTotal() {
                return maxTotal;
        }

        public void setMaxTotal(Integer maxTotal) {
                this.maxTotal = maxTotal;
        }

        public Integer getMaxPerRoute() {
                return maxPerRoute;
        }

        public void setMaxPerRoute(Integer maxPerRoute) {
                this.maxPerRoute = maxPerRoute;
        }

        public Integer getConnectionRequestTimeout() {
                return connectionRequestTimeout;
        }

        public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
                this.connectionRequestTimeout = connectionRequestTimeout;
        }

        public Integer getConnectTimeout() {
                return connectTimeout;
        }

        public void setConnectTimeout(Integer connectTimeout) {
                this.connectTimeout = connectTimeout;
        }

        public Integer getSocketTimeout() {
                return socketTimeout;
        }

        public void setSocketTimeout(Integer socketTimeout) {
                this.socketTimeout = socketTimeout;
        }
}
