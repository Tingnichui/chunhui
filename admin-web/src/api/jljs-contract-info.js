import http from "@/util/http";

export const pageJljsContractInfoList = (data) => {
return http.post("/jljsContractInfo/pageList", data);
};

export const saveJljsContractInfo = (data) => {
return http.post("/jljsContractInfo/save", data);
};

export const updateJljsContractInfo = (data) => {
return http.post("/jljsContractInfo/update", data);
};

export const getJljsContractInfoDetail = (id) => {
return http.postForm("/jljsContractInfo/detail", {id});
};

export const deleteJljsContractInfoById = (id) => {
return http.postForm("/jljsContractInfo/delete", {id});
};
