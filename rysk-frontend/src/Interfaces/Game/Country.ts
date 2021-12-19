import { Player } from "../Player/Player";

export interface Country {
  countryId: number;
  controlledBy: Player;
  name: NAME;
  printableName: string;
  armies: number;
}

export enum NAME {
  Alaska,
  Alberta,
  CentralAmerica,
  EasternUnitedStates,
  Greenland,
  NorthwestTerritory,
  Ontario,
  Quebec,
  WesternUnitedStates,
  Argentina,
  Brazil,
  Peru,
  Venezuela,
  GreatBritain,
  Iceland,
  NorthernEurope,
  Scandinavia,
  SouthernEurope,
  Ukraine,
  WesternEurope,
  Congo,
  EastAfrica,
  Egypt,
  Madagascar,
  NorthAfrica,
  SouthAfrica,
  Afghanistan,
  China,
  India,
  Irkutsk,
  Japan,
  Kamchatka,
  MiddleEast,
  Mongolia,
  Siam,
  Siberia,
  Ural,
  Yakutsk,
  EasternAustralia,
  Indonesia,
  NewGuinea,
  WesternAustralia,
}
