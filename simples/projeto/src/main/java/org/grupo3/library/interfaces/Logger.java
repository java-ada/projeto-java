package org.grupo3.library.interfaces;

import java.io.IOException;
import java.util.logging.FileHandler;

public interface Logger {
    void updateLog(String type, String operator, String title) throws IOException;
    String logLineBuilder(String type,String operator, String title);
    FileHandler createLogFile(String type) throws IOException, SecurityException;
}
