package whut.factory;

import whut.interfaces.Login;
import whut.service.BookAdminService;
import whut.service.StudentService;
import whut.service.SysAdminService;

import java.io.IOException;

public class LoginFactory {

    public static Login getLoginService(int ident) throws IOException {
        switch (ident) {
            case Login.STU: return StudentService.GetStudentService();
            case Login.BOOK_ADMIN: return BookAdminService.GetAdminService();
            case Login.SYS_ADMIN: return SysAdminService.GetSysAdminService();
            default:throw new IOException("no such ident!");
        }
    }
}
