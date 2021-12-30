package whut.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetResponse {
    /**
     * 生成相应的输出流
     * @param resp HTTP响应
     * @param encode 响应编码
     * @param respInfo 响应的信息
     * @return 返回响应的输出流
     */
    public static PrintWriter getRespWriter (HttpServletResponse resp, String encode, Object respInfo) throws IOException {
        resp.setCharacterEncoding(encode);
        PrintWriter out = resp.getWriter();
        String JSONStr = JSON.toJSONString(respInfo);
        out.append(JSONStr);
        return out;
    }
}
