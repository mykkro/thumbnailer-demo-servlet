package cz.cvut.kbss.mondis.thumbnailerdemo;


import cz.cvut.kbss.mondis.thumbnailer.Thumbnailer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by myrousz on 11/13/14.
 */
public class ThumbnailerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        URL url = new URL(request.getParameter("q"));
        Thumbnailer thumbnailer = Thumbnailer.fromProperties(Thumbnailer.getDefaultProperties());
        BufferedImage image = thumbnailer.getThumbnail(url);
        if(image != null) {
            response.setContentType("image/jpeg");
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
            outputStream.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println( "Failed to generate thumbnail!" );
            out.flush();
            out.close();
        }
    }
}
