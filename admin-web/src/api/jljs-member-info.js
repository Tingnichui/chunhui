import http from "@/util/http";

export const pageJljsMemberInfoList = (data) => {
return http.post("/jljsMemberInfo/pageList", data);
};

export const saveJljsMemberInfo = (data) => {
return http.post("/jljsMemberInfo/save", data);
};

export const updateJljsMemberInfo = (data) => {
return http.post("/jljsMemberInfo/update", data);
};

export const getJljsMemberInfoDetail = (id) => {
return http.postForm("/jljsMemberInfo/detail", {id});
};

export const deleteJljsMemberInfoById = (id) => {
return http.postForm("/jljsMemberInfo/delete", {id});
};
