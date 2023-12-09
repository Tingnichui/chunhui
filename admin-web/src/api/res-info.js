import http from "@/util/http";

export const pageList = (data) => {
    return http.post("/resInfo/pageList", data);
};

export const save = (data) => {
    return http.post("/resInfo/save", data);
};

export const update = (data) => {
    return http.post("/resInfo/update", data);
};

export const detail = (id) => {
    return http.postForm("/resInfo/detail", {id});
};

export const deleteById = (id) => {
    return http.postForm("/resInfo/delete", {id});
};
