package control;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HeaderSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inizializzazione del filtro, se necessario
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Aggiungi gli header anti-clickjacking alla risposta HTTP
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        httpResponse.setHeader("Content-Security-Policy", "frame-ancestors 'self'");

        // Passa la richiesta al prossimo filtro nella catena
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Distruggi il filtro, se necessario
    }
}
