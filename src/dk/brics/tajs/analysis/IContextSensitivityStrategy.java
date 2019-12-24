/*
 * Copyright 2009-2019 Aarhus University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dk.brics.tajs.analysis;

import dk.brics.tajs.flowgraph.AbstractNode;
import dk.brics.tajs.flowgraph.Function;
import dk.brics.tajs.flowgraph.jsnodes.BeginForInNode;
import dk.brics.tajs.flowgraph.jsnodes.BeginLoopNode;
import dk.brics.tajs.flowgraph.jsnodes.EndLoopNode;
import dk.brics.tajs.lattice.Context;
import dk.brics.tajs.lattice.ObjectLabel;
import dk.brics.tajs.lattice.State;
import dk.brics.tajs.lattice.Value;

/**
 * Strategy-pattern interface for context sensitivity strategies.
 */
public interface IContextSensitivityStrategy {

    /**
     * Constructs a heap context for a function object.
     */
    Context makeFunctionHeapContext(Function fun, Solver.SolverInterface c);

    /**
     * Constructs a heap context for objects related to a call.
     */
    Context makeActivationAndArgumentsHeapContext(State state, ObjectLabel function, FunctionCalls.CallInfo callInfo, Solver.SolverInterface c);

    /**
     * Constructs a heap context for an object created at 'new'.
     */
    Context makeConstructorHeapContext(State state, ObjectLabel function, FunctionCalls.CallInfo callInfo, Solver.SolverInterface c);

    /**
     * Constructs a heap context for an object literal.
     */
    Context makeObjectLiteralHeapContext(AbstractNode node, State state, Solver.SolverInterface c);

    /**
     * Constructs a heap context for a boxed primitive.
     */
    Context makeBoxedPrimitiveHeapContext(Value primitive);

    /**
     * Constructs the initial context.
     */
    Context makeInitialContext();

    /**
     * Constructs a context for a call.
     */
    Context makeFunctionEntryContext(State state, ObjectLabel function, FunctionCalls.CallInfo callInfo, Solver.SolverInterface c);

    /**
     * Constructs a context for entering a for-in body.
     */
    Context makeForInEntryContext(Context currentContext, BeginForInNode n, Value v);

    /**
     * Constructs a context for (re-)entering a loop.
     */
    Context makeNextLoopUnrollingContext(Context currentContext, BeginLoopNode node);

    /**
     * Constructs a context for leaving a loop.
     */
    Context makeLoopExitContext(Context currentContext, EndLoopNode node);

    /**
     * Requests that a parameter is treated context sensitively.
     */
    void requestContextSensitiveParameter(Function function, String parameter);
}
