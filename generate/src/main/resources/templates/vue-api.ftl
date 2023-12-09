import http from "@/util/http";

export const pageList = (data) => {
return http.post("/${tableHump}/pageList", data);
};

export const save = (data) => {
return http.post("/${tableHump}/save", data);
};

export const update = (data) => {
return http.post("/${tableHump}/update", data);
};

export const detail = (id) => {
return http.postForm("/${tableHump}/detail", {id});
};

export const deleteById = (id) => {
return http.postForm("/${tableHump}/delete", {id});
};
