import http, {createBaseAxios} from "@/util/http";
import axios from "axios";

export const login = (data) => {
    return http({
        url: "/sysUser/login",
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

export const getUploadAccess = (data) => {
    return http.post("/sysFile/getUploadAccess", data);
};

export const getDownLoadAccess = (id) => {
    return http.postForm("/sysFile/getDownLoadAccess", {id});
};

export const saveResInfo = (data) => {
    return http.post("/resInfo/save", data);
};

export const updateResInfo = (data) => {
    return http.post("/resInfo/update", data);
};

export const getResInfoById = (id) => {
    return http.postForm("/resInfo/getResInfoById", {id});
};

export const resPageList = (data) => {
    return http.post("/resInfo/pageList", data);
};

export const uploadOss = (url, data, option) => {
    const formData = new FormData()
    if (data) {
        for (const [key, value] of Object.entries(data)) {
            // if (isArray(value) && value.length) formData.append(key, ...value)
            formData.append(key, value)
        }
    }
    formData.append(option.filename, option.file, option.file.name)
    return axios.create({timeout: 10000}).post(url, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }
    );
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

export const deleteById = (id) => {
    return http.postForm("/sysUser/delete", {id});
};