import http from "@/util/http";

export const pageJljsClassRecordList = (data) => {
return http.post("/jljsClassRecord/pageList", data);
};

export const saveJljsClassRecord = (data) => {
return http.post("/jljsClassRecord/save", data);
};

export const updateJljsClassRecord = (data) => {
return http.post("/jljsClassRecord/update", data);
};

export const getJljsClassRecordDetail = (id) => {
return http.postForm("/jljsClassRecord/detail", {id});
};

export const deleteJljsClassRecordById = (id) => {
return http.postForm("/jljsClassRecord/delete", {id});
};
