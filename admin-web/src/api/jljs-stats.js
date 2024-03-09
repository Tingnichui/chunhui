import http from "@/util/http";

export const getClassStatsByDay = (data) => {
return http.post("/jljsStats/getClassStatsByDay", data);
};
export const getMemberBirthStats = (data) => {
return http.post("/jljsStats/getMemberBirthStats", data);
};
