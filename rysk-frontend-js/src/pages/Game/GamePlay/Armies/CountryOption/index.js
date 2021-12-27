import React from "react";
import { Form } from "react-bootstrap";

export const CountryOption = (props) => {
  return (
    <option value={props.country.countryId}>
      {props.country.printableName}, Armies: {props.country.armies}
    </option>
  );
};
