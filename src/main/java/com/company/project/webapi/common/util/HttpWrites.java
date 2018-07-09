package com.company.project.webapi.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class HttpWrites {

    public static void write(HttpServletResponse response, String msg) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
