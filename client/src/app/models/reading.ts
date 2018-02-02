import {Tires} from './reading.tires';

export class Reading {
  vin: string;
  latitude: number;
  longitude: number;
  timestamp: any;
  fuelVolume: number;
  speed: number;
  engineHp: number;
  checkEngineLightOn: boolean;
  engineCoolantLow: boolean;
  cruiseControlOn: boolean;
  engineRpm: number;
  tires: Tires;
}
