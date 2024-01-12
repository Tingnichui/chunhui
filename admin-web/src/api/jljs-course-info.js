import http from "@/util/http";

export const pageJljsCourseInfoList = (data) => {
return http.post("/jljsCourseInfo/pageList", data);
};

export const saveJljsCourseInfo = (data) => {
return http.post("/jljsCourseInfo/save", data);
};

export const updateJljsCourseInfo = (data) => {
return http.post("/jljsCourseInfo/update", data);
};

export const getJljsCourseInfoDetail = (id) => {
return http.postForm("/jljsCourseInfo/detail", {id});
};

export const deleteJljsCourseInfoById = (id) => {
return http.postForm("/jljsCourseInfo/delete", {id});
};
