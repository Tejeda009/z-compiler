# Z benchmark results

Generated on `2026-11-17T19:20:35Z` from commit `b0b1d60` (`Merge pull request #245 from the-big-zed/benchmarks/results`) on branch `main`.

Positive `Z vs C` means Z is faster. The headline uses the fastest run of each binary. The target is a geometric mean of at least 1.20x across the suite.

## Summary

| Metric | Value |
| --- | ---: |
| Cases | 18 |
| Geometric mean speedup (best) | 1.109x |
| Median case speedup (best) | 1.000x |
| Geometric mean speedup (median) | 1.128x |
| Z faster | 7 |
| C faster | 11 |
| Ties | 0 |
| Total C best | 3628.096 ms |
| Total Z best | 3554.475 ms |
| Highest C RSD | 20.38% (pi) |
| Highest Z RSD | 13.32% (hash) |

Run-to-run variance above 5%: `pi`, `hash`. Treat those speedups as indicative and rerun on an idle machine.

## Categories

| Category | Cases | Geometric mean (best) | Geometric mean (median) |
| --- | ---: | ---: | ---: |
| alias | 1 | 1.000x | 1.000x |
| allocation | 1 | 2.081x | 2.075x |
| calls | 1 | 1.000x | 1.000x |
| compute | 7 | 1.039x | 1.086x |
| float | 1 | 1.000x | 1.000x |
| integer | 4 | 1.000x | 1.000x |
| memory | 1 | 1.437x | 1.437x |
| pointers | 1 | 0.810x | 0.811x |
| strings | 1 | 2.035x | 2.021x |

## Results

| Benchmark | Category | C best (ms) | Z best (ms) | Z vs C | C median (ms) | Z median (ms) | C RSD | Z RSD |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| fibonacci | compute | 164.307 | 164.356 | -0.0% | 164.434 | 164.461 | 0.07% | 0.08% |
| collatz | compute | 264.531 | 264.592 | -0.0% | 264.969 | 264.796 | 0.07% | 0.06% |
| pi | compute | 63.924 | 63.929 | -0.0% | 114.348 | 64.160 | 20.38% | 0.29% |
| primes | compute | 214.341 | 214.441 | -0.0% | 214.545 | 214.547 | 0.10% | 0.09% |
| ackermann | compute | 63.935 | 64.009 | -0.1% | 64.150 | 64.167 | 0.25% | 0.17% |
| mandelbrot | compute | 114.309 | 114.256 | +0.0% | 114.529 | 114.466 | 0.12% | 0.08% |
| hash | compute | 214.423 | 164.215 | +23.4% | 214.517 | 214.474 | 0.02% | 13.32% |
| alias | alias | 214.337 | 214.301 | +0.0% | 214.443 | 214.432 | 0.06% | 0.08% |
| alloc_churn | allocation | 15.501 | 7.449 | +51.9% | 15.543 | 7.489 | 0.17% | 0.37% |
| string_concat | strings | 31.820 | 15.633 | +50.9% | 31.886 | 15.781 | 0.11% | 0.74% |
| fib | calls | 364.593 | 364.528 | +0.0% | 364.762 | 364.636 | 0.08% | 0.09% |
| collatz | integer | 264.368 | 264.482 | -0.0% | 264.658 | 264.613 | 0.04% | 0.03% |
| gcd_sum | integer | 364.594 | 364.670 | -0.0% | 365.295 | 365.243 | 0.87% | 0.09% |
| primes | integer | 214.589 | 214.612 | -0.0% | 214.818 | 214.907 | 0.05% | 0.07% |
| digit_sum | integer | 364.967 | 365.021 | -0.0% | 365.285 | 365.292 | 0.04% | 0.05% |
| monte_carlo | float | 314.839 | 314.990 | -0.0% | 315.090 | 315.156 | 0.07% | 0.03% |
| concat_chain | memory | 164.403 | 114.377 | +30.4% | 164.694 | 114.608 | 0.07% | 0.11% |
| ptr_accum | pointers | 214.317 | 264.614 | -23.5% | 214.782 | 264.888 | 0.11% | 0.06% |

`best` is the fastest timed run and is the headline comparison. `median` and RSD show how stable the sample is.

## Method

- C flags: `-O3 -march=native -flto -funroll-loops -mtune=native -std=c17 -fno-builtin`.
- Z pipeline: `opt -O3 -mcpu=native; clang -O3 -flto -march=native -funroll-loops -mtune=native`.
- Warmup runs per binary: 2.
- Timed runs per binary: 10.
- Pinning: CPU 1.
- Statistics: best-of-N and median wall-clock time, plus relative standard deviation; interleaved execution.
- Correctness: both binaries must exit successfully and print the same result before timing.

## Environment

| Field | Value |
| --- | --- |
| OS | Ubuntu 26.04.1 LTS |
| Kernel | 7.0.0-1016-aws |
| CPU | Intel(R) Xeon(R) 6975P-C |
| Cores | 4 |
| Affinity | [0, 1, 2, 3] |
| CPU governor | unavailable |
| Load average | [1.0, 0.67, 0.28] |
| Clang | Ubuntu clang version 22.1.2 (1ubuntu1) |
| opt | Ubuntu LLVM version 22.1.2 |
| Java | openjdk version "21.0.13" 2026-10-20 LTS |
| Working tree | clean |

## Reproduce

```sh
python3 benchmarks/bench.py --runs 10 --warmup 2
```
