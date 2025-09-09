package org.example.o_lim.common.constants;


import org.springframework.boot.web.reactive.context.StandardReactiveWebEnvironment;

public class ApiMappingPattern {
    public static final String API = "/api";
    public static final String V1 = "/v1";
    public static final String BASE = API + V1;

    public static final class Auth {

    }

    public static final class Users {
        private Users() {}

        public static final String ROOT = BASE + "/users";

        public static final String SIGN_UP = BASE + "/signup";
        public static final String LOG_IN = BASE + "/login";
        public static final String UPDATE_MY_INFO = ROOT + "/me";
        public static final String SEARCH_PROFILE = ROOT + "/me";
        public static final String FIND_ID = ROOT + "/find-id";
        public static final String RESET_PASSWORD = ROOT + "/reset-pw";
    }

    public static final class Projects {
        // CR(전체/단건/업무순)UD
        private Projects() {}

        public static final String ROOT = BASE + "/projects"; // controller 클래스 단위 (RequestMapping(ROOT))
        public static final String ID_ONLY = "/{projectId}";

        // 메서드 단위 - 각 기능별
        // : 컨트롤러 단위 경로 이후 값 지정
        public static final String BY_ID = ROOT + "/{projectId}";

//        public static final String UPDATE = ROOT + "/{projectId}";
//        public static final String DELETE = ROOT + "/{projectId}";
        public static final String SEARCH_ALL = ROOT + "/all"; // 생략 가능
//        public static final String SEARCH_SINGLE = ROOT + "/{projectId}";
        public static final String SEARCH_BY_TASK_DESC = "/task-desc";
    }

    public static final class Tasks {
        private Tasks() {}

        public static final String ROOT = Projects.BY_ID + "/tasks";

        public static final String BY_ID = BASE + "/tasks/{taskId}";

        public static final String SEARCH_FILTER_ALL = ROOT + "/assignee/{userId}";
        public static final String SEARCH_SINGLE = BASE + "/tasks/{taskId}";

        public static final String UPDATE = BASE + "/tasks/{taskId}";
    }

    public static final class Tags {
        private Tags() {}

        public static final String ROOT = Projects.BY_ID + "/tags";
        public static final String DELETE = ROOT + "/{tagId}";
    }

    public static final class Comments {
        private Comments() {}

        public static final String ROOT = Tasks.BY_ID + "/comments";

        public static final String DELETE = ROOT + "/{commentId}";

        public static final String SEARCH_COMMENT_IN_TASK_BY_NEW = ROOT;
    }

    public static final class Notification {
        private Notification() {}

        public static final String ROOT = BASE + "/notices";

        public static final String SEARCH_ALL = ROOT;
        public static final String SEARCH_SINGLE = ROOT + "/{noticeId}";

        public static final String UPDATE = ROOT + "/{noticeId}";
        public static final String DELETE = ROOT + "/{noticeId}";
    }


}
