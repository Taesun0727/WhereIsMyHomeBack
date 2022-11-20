package com.mvc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "Deal : 아파트거레정보", description = "아파트의 거래 정보를 나타낸다.")
public class Deal {
	
	@ApiModelProperty(value = "매매가격")
	private String dealAmount;
	@ApiModelProperty(value = "거래년도")
	private String dealYear;
	@ApiModelProperty(value = "거래월")
	private String dealMonth;
	@ApiModelProperty(value = "거래일")
	private String dealDay;
	@ApiModelProperty(value = "면적")
	private String area;
	@ApiModelProperty(value = "층수")
	private String floor;
	
}