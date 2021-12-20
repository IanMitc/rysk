import { Player } from "../Player/Player";
import { Country } from "./Country";
import { GameLog } from "./GameLog";
import { Hand } from "./Hand";

export interface GameState {
  gameId: number;
  players: Player[];
  currentPlayer: Player;
  attackingPlayer?: Player;
  attackingCountry?: Country;
  defendingCountry?: Country;
  playersCards?: Hand;
  logs: GameLog[];
  countries: Country[];
  attackingDice1?: number;
  attackingDice2?: number;
  attackingDice3?: number;
  defendingDice1?: number;
  defendingDice2?: number;
  armiesToPlay: number;
  playerWon: boolean;
  bonusArmies: number;
  stage: STAGE;
}

export enum STAGE {
  DISCARD,
  ARMIES,
  ATTACK,
  DEFEND,
  MOVE,
  DRAW,
}
