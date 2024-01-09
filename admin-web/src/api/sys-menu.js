import http from "@/util/http";

export const pageMenuList = (data) => {
    return http.post("/sysMenu/pageList", data);
};

export const save = (data) => {
    return http.post("/sysMenu/save", data);
};

export const update = (data) => {
    return http.post("/sysMenu/update", data);
};

export const detail = (id) => {
    return http.postForm("/sysMenu/detail", {id});
};

export const deleteById = (id) => {
    return http.postForm("/sysMenu/delete", {id});
};

export const getHomeMenu = () => {
    return http.post("/sysMenu/getMenuByCurrentUser");
};
