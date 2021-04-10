package com.tiehca.apitest.heshang.common.filter;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chen9
 */
@WebFilter(filterName = "fileValidateFilter",urlPatterns="/*")
public class FileValidateFilter implements Filter {

    private static final String MULTIPART_FILE = "multipart";
    private static final int FILE_SIZE_LIMIT = 1024 * 1024;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String contentType = servletRequest.getContentType();
        if (contentType != null && contentType.contains(MULTIPART_FILE)) {
            StandardMultipartHttpServletRequest re = new StandardMultipartHttpServletRequest((HttpServletRequest) servletRequest);
            MultiValueMap<String, MultipartFile> fileMap = re.getMultiFileMap();
            AtomicBoolean check = new AtomicBoolean(true);
            fileMap.forEach((k,v) -> {
                v.forEach(t -> {
                    try {
                        servletResponse.setCharacterEncoding("UTF-8");
                        if (!isImage(Objects.requireNonNull(t.getOriginalFilename()).toUpperCase())){
                            servletResponse.getWriter().println("请不要上传图片以外的文件 : " + t.getOriginalFilename());
                            check.set(false);
                        } else if (t.getSize() > FILE_SIZE_LIMIT) {
                            servletResponse.getWriter().println("请不要上传超过1M的图片文件 : " + t.getOriginalFilename());
                            check.set(false);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                });
            });
            if (check.get()) {
                filterChain.doFilter(re,servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    private boolean isImage(String name) {
        return name.endsWith("JPG")
                || name.endsWith("JEPG")
                || name.endsWith("PNG");
    }

    @Override
    public void destroy() {

    }
}
