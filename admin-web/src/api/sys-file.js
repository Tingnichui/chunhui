import http from "@/util/http";

export const getUploadAccess = (data) => {
    return http.post("/sysFile/getUploadAccess", data);
};

export const getDownLoadAccess = (id) => {
    return http.postForm("/sysFile/getDownLoadAccess", {id});
};