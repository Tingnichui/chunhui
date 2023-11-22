import http from "@/util/http";

export const login = (params) => {
    return http({
        url: "/sys-user/login",
        method: "post",
        params,
    });
};