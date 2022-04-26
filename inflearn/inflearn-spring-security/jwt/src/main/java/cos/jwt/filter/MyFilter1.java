package cos.jwt.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터1");
        filterChain.doFilter(servletRequest, servletResponse); // 다음 필터도 동작하기 위해 필수
    }
}
