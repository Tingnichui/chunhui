import http from "@/util/http";

export const getClassStatsByDay = (data) => {
return http.post("/jljsStats/getClassStatsByDay", data);
};
