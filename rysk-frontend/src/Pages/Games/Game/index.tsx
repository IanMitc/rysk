import React, {useState} from "react";

export type GameProps = {
    id: number,
    name: string,
    numberOfCountries: number,
    numberOfPlayers: number,
}

export const Game = (props: GameProps)  => {
    const [game, setGame] = useState();

    return <React.Fragment>
        <tr>
            <td>props.game.id</td>
            <td>props.game.name</td>
            <td>props.game.numberOfCountries</td>
            <td>props.game.numberOfPlayers</td>
            <td></td>
        </tr>
    </React.Fragment>    
};

