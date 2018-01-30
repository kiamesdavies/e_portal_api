/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  poseidon
 * Created: Jan 22, 2018
 */

CREATE VIEW device_positions_view_aggregate AS select
	p.id,
	address,
	latitude,
	longitude,
	`time`,
	course,
	description,
	iconArrowMovingColor,
	iconArrowOfflineColor,
	iconArrowPausedColor,
	iconArrowRadius,
	iconArrowStoppedColor,
device_id,
	name
from
	positions p join devices d on
	p.device_id = d.id
