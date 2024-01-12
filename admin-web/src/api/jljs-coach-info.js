import http from "@/util/http";

export const pageJljsCoachInfoList = (data) => {
return http.post("/jljsCoachInfo/pageList", data);
};

export const saveJljsCoachInfo = (data) => {
return http.post("/jljsCoachInfo/save", data);
};

export const updateJljsCoachInfo = (data) => {
return http.post("/jljsCoachInfo/update", data);
};

export const getJljsCoachInfoDetail = (id) => {
return http.postForm("/jljsCoachInfo/detail", {id});
};

export const deleteJljsCoachInfoById = (id) => {
return http.postForm("/jljsCoachInfo/delete", {id});
};
