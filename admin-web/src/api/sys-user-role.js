import http from "@/util/http";

export const pageList = (data) => {
    return http.post("/sysUserRole/pageList", data);
};

export const saveSysUserRole = (data) => {
    return http.post("/sysUserRole/save", data);
};

export const update = (data) => {
    return http.post("/sysUserRole/update", data);
};

export const detail = (id) => {
    return http.postForm("/sysUserRole/detail", {id});
};

export const deleteById = (id) => {
    return http.postForm("/sysUserRole/delete", {id});
};
