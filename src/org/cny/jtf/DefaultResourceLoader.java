package org.cny.jtf;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.URL;

/**
 * @author Centny
 */
public class DefaultResourceLoader extends org.springframework.core.io.DefaultResourceLoader {
    @Override
    public Resource getResource(String location) {
        try {
            return new UrlResource(new URL(location));
        } catch (Exception e1) {
            try {
                return new FileSystemResource(location);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }
}
