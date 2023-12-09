import http from "@/util/http";

export const deleteById = (id) => {
    return http.postForm("/resInfo/deleteById", {id});
};