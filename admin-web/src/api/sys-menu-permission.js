import http from "@/util/http";

export const pageList = (data) => {
    return http.post("/sysMenuPermission/pageList", data);
};

export const save = (data) => {
    return http.post("/sysMenuPermission/save", data);
};

export const update = (data) => {
    return http.post("/sysMenuPermission/update", data);
};

export const detail = (id) => {
    return http.postForm("/sysMenuPermission/detail", {id});
};

export const deleteById = (id) => {
    return http.postForm("/sysMenuPermission/delete", {id});
};
