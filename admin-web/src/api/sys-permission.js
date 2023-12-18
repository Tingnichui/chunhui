import http from "@/util/http";

export const pagePermissionList = (data) => {
return http.post("/sysPermission/pageList", data);
};

export const save = (data) => {
return http.post("/sysPermission/save", data);
};

export const update = (data) => {
return http.post("/sysPermission/update", data);
};

export const detail = (id) => {
return http.postForm("/sysPermission/detail", {id});
};

export const deleteById = (id) => {
return http.postForm("/sysPermission/delete", {id});
};
