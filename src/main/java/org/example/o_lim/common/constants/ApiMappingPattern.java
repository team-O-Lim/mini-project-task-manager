package org.example.o_lim.common.constants;

public class ApiMappingPattern {
    public static final String API = "/api";
    public static final String V1 = "/v1";
    public static final String BASE = API + V1;

    public static final class Users {
        private Users() {}

        public static final String ROOT = BASE + "/users";

        public static final String MY_INFO = "/me";

        // public static final String SEARCH_PROFILE = ROOT + "/me";
    }
    public static final class Auth {
        private Auth() {}

        public static final String ROOT = BASE + "/auth";

        public static final String SIGN_UP = "/sign-up";
        public static final String SIGN_IN = "/sign-in";
        public static final String FIND_ID = "/find-id";
        public static final String RESET_PASSWORD = "/reset-pw";
    }
    public static final class Admin {
        private Admin() {
        }

        public static final String ROOT = BASE + "/admin";

        public static final String REPLACE = "/roles/replace";
        public static final String ADD = "/roles/add";
        public static final String REMOVE = "/roles/remove";

    }

    public static final class Projects {
        private Projects() {}

        public static final String ROOT = BASE + "/projects";

        public static final String BY_ID = "/{projectId}";

        public static final String SEARCH_BY_TASK_DESC = "/task-desc";

        //public static final String SEARCH_ALL = ROOT + "/all";
        //public static final String ID_ONLY = "/{projectId}";
        // public static final String UPDATE = ROOT + "/{projectId}";
        // public static final String DELETE = ROOT + "/{projectId}";
        // public static final String SEARCH_SINGLE = ROOT + "/{projectId}";
    }

    public static final class Tasks {
        private Tasks() {}

        // == Controller 단위 경로 == //
        public static final String ROOT = "/projects/{project}/tasks";

        // == 단건 조회(1개만 반환), 수정, 삭제 == //
        public static final String BY_ID = "/{taskId}";

        // == 검색 & 필터링 (다건 조회)== //
        public static final String FILTER_CREATED_USER = "/created-user/{createdUser}";

        // 검색 조회
        public static final String SEARCH = "/search";

        // public static final String SEARCH_SINGLE = BASE + "/tasks/{taskId}";
        // public static final String UPDATE = "/tasks/{taskId}";
    }

    public static final class Tags {
        private Tags() {}

        public static final String ROOT = BASE + "/projects/{projectId}/tags";

        public static final String BY_ID = "/{tagId}";

        // public static final String DELETE = ROOT + "/{tagId}";
    }

    public static final class Comments {
        private Comments() {}

        public static final String ROOT = BASE + "/tasks/{taskId}/comments";

        public static final String BY_ID = "/{commentId}";

        // public static final String DELETE = ROOT + "/{commentId}";
    }

    public static final class Notification {
        private Notification() {}

        public static final String ROOT = BASE + "/notifications";

        public static final String BY_ID = "/{notificationId}";

        // public static final String SEARCH_ALL = "/all";
        // public static final String UPDATE = ROOT + "/{noticeId}";
        // public static final String DELETE = ROOT + "/{noticeId}";
    }

}
