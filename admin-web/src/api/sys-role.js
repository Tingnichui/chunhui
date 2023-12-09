import http from "@/util/http";

export const pageList = (data) => {
return http.post("/sysRole/pageList", data);
};

export const save = (data) => {
return http.post("/sysRole/save", data);
};

export const update = (data) => {
return http.post("/sysRole/update", data);
};

export const detail = (id) => {
return http.postForm("/sysRole/detail", {id});
};

export const deleteById = (id) => {
return http.postForm("/sysRole/delete", {id});
};
