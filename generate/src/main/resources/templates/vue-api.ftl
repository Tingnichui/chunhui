import http from "@/util/http";

export const page${tableBigHump}List = (data) => {
return http.post("/${tableHump}/pageList", data);
};

export const save${tableBigHump} = (data) => {
return http.post("/${tableHump}/save", data);
};

export const update${tableBigHump} = (data) => {
return http.post("/${tableHump}/update", data);
};

export const get${tableBigHump}Detail = (id) => {
return http.postForm("/${tableHump}/detail", {id});
};

export const delete${tableBigHump}ById = (id) => {
return http.postForm("/${tableHump}/delete", {id});
};
