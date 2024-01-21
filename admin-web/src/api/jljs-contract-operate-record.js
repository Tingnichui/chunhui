import http from "@/util/http";

export const pageJljsContractOperateRecordList = (data) => {
return http.post("/jljsContractOperateRecord/pageList", data);
};

export const saveJljsContractOperateRecord = (data) => {
return http.post("/jljsContractOperateRecord/save", data);
};

export const updateJljsContractOperateRecord = (data) => {
return http.post("/jljsContractOperateRecord/update", data);
};

export const getJljsContractOperateRecordDetail = (id) => {
return http.postForm("/jljsContractOperateRecord/detail", {id});
};

export const deleteJljsContractOperateRecordById = (id) => {
return http.postForm("/jljsContractOperateRecord/delete", {id});
};
export const revokeJljsContractOperateRecordById = (id) => {
return http.postForm("/jljsContractOperateRecord/revoke", {id});
};
