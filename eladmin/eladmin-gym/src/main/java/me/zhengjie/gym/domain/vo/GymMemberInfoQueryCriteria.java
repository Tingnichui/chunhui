/*
*  Copyright 2019-2023 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.gym.domain.vo;

import lombok.Data;
import java.util.List;

/**
* @author genghui
* @date 2024-06-30
**/
@Data
public class GymMemberInfoQueryCriteria {
    private String memberName;
    private String memberGender;
    private String memberPhoneNum;
    private String birthDay;
    private List<String> memberAge;
}