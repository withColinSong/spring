package cos.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터3");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        // 토큰을 만들어줘야 함.
        // 요청할 때 마다 header에 Authorization에 value값으로 토큰을 가지고 온다.
        // 그때 토근이 넘어오면 이 토큰이 내가 만든 토큰인지만 확인하면 됨.
        if(req.getMethod().equals("POST")) {
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            if(headerAuth.equals("cos")) {
                filterChain.doFilter(servletRequest, servletResponse); // 다음 필터도 동작하기 위해 필수
            } else {
                PrintWriter out = res.getWriter();
                out.println("인증안됨");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
