import http from "@/util/http";

export const login = (data) => {
    return http({
        url: "/sysUser/login",
        method: "post",
        data,
    });
};

export const logout = (data) => {
    return http({
        url: "/sysUser/logout",
        method: "post",
        data,
    });
};



export const regist = (data) => {
    return http({
        url: "/sysUser/regist",
        method: "post",
        data,
    });
};

export const pageList = (data) => {
    return http.post("/sysUser/pageList", data);
};

export const save = (data) => {
    return http.post("/sysUser/save", data);
};

export const update = (data) => {
    return http.post("/sysUser/update", data);
};

export const detail = (id) => {
    return http.postForm("/sysUser/detail", {id});
};

export const getCurrentUserDetail = () => {
    return http.get("/sysUser/getCurrentUserDetail");
};

export const deleteById = (id) => {
    return http.postForm("/sysUser/delete", {id});
};