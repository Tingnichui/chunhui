import http from "@/util/http";

export const pageList = (data) => {
return http.post("/sysRolePermission/pageList", data);
};

export const save = (data) => {
return http.post("/sysRolePermission/save", data);
};

export const update = (data) => {
return http.post("/sysRolePermission/update", data);
};

export const detail = (id) => {
return http.postForm("/sysRolePermission/detail", {id});
};

export const deleteById = (id) => {
return http.postForm("/sysRolePermission/delete", {id});
};
