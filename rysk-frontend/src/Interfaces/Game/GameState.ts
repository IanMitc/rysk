import { Player } from "../Player/Player";

export interface GameState {
    gameId: number;
    players: Player[];
    currentPlayer: Player;
    attackingPlayer: Player;
    
}