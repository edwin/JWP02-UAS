/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubl.jwp.servlet;

import com.ubl.jwp.service.DosenService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edwin < edwinkun at gmail dot com >
 */
public class DosenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        String jsonObject = "{\"dosens\":[";
        
        String[][] dosens = new DosenService().getDosens();
        for (String[] dosen : dosens) {
            jsonObject += "{";
            jsonObject += "\"iddosen\": \""+dosen[0]+"\", \"namadosen\": \""+dosen[1]+"\"";
            jsonObject += "},";
        }
        
        // remove last comma
        jsonObject = jsonObject.substring(0, jsonObject.length()-1);
        jsonObject += "]}";
        
        out.print(jsonObject);
        out.flush();
    }

}
