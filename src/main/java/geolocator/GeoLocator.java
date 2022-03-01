package geolocator;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;

/**
 * Interface for obtaining geolocation information about an
 * IP address or host
 * name. Geolocation information is obtained from the
 * <a href ="https://ip-api.com/">IP-API</a> service.
 * A new instance implementing the interface
 * can be obtained via the {@link #newInstance()} method.
 */
public interface GeoLocator {

    /**
     * Returns geolocation information about the JVM running the application.
     *
     * @return an object wrapping the information returned
     * @throws feign.FeignException if any error occurs
     */
    @RequestLine("GET")
    GeoLocation getGeoLocation();

    /**
     * Returns geolocation information about the IP address or host name specified.
     *
     * @param ipOrHostName an IP address or host name
     * @return an object wrapping the information returned
     * @throws feign.FeignException if any error occurs
     */
    @RequestLine("GET /{ipOrHostName}")
    GeoLocation getGeoLocation(@Param("ipOrHostName") String ipOrHostName);

    /**
     * Creates and returns a new object implementing the interface.
     *
     * @return a new object implementing the interface
     */
    static GeoLocator newInstance() {
        return Feign.builder()
            .decoder(new JacksonDecoder())
            .target(GeoLocator.class, "http://ip-api.com/json/");
    }

}
