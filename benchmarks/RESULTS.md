# Z benchmark results

Generated on `2026-11-16T18:12:07Z` from commit `4dc76c0` (`Merge pull request #244 from the-big-zed/Tejeda009-ci-cd-pr-benchmarka`) on branch `main`.

Positive `Z vs C` means Z is faster. The headline uses the fastest run of each binary. The target is a geometric mean of at least 1.20x across the suite.

## Summary

| Metric | Value |
| --- | ---: |
| Cases | 18 |
| Geometric mean speedup (best) | 1.109x |
| Median case speedup (best) | 1.000x |
| Geometric mean speedup (median) | 1.109x |
| Z faster | 9 |
| C faster | 9 |
| Ties | 0 |
| Total C best | 3622.360 ms |
| Total Z best | 3548.183 ms |
| Highest C RSD | 4.27% (concat_chain) |
| Highest Z RSD | 5.95% (hash) |

Run-to-run variance above 5%: `hash`. Treat those speedups as indicative and rerun on an idle machine.

## Categories

| Category | Cases | Geometric mean (best) | Geometric mean (median) |
| --- | ---: | ---: | ---: |
| alias | 1 | 1.000x | 1.000x |
| allocation | 1 | 2.089x | 2.081x |
| calls | 1 | 1.000x | 1.000x |
| compute | 7 | 1.039x | 1.039x |
| float | 1 | 1.000x | 1.000x |
| integer | 4 | 1.000x | 1.000x |
| memory | 1 | 1.438x | 1.438x |
| pointers | 1 | 0.811x | 0.811x |
| strings | 1 | 2.036x | 2.033x |

## Results

| Benchmark | Category | C best (ms) | Z best (ms) | Z vs C | C median (ms) | Z median (ms) | C RSD | Z RSD |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| fibonacci | compute | 163.899 | 163.909 | -0.0% | 164.123 | 164.128 | 0.11% | 0.10% |
| collatz | compute | 264.043 | 264.046 | -0.0% | 264.351 | 264.399 | 0.09% | 0.08% |
| pi | compute | 63.706 | 63.695 | +0.0% | 63.879 | 63.906 | 0.16% | 0.15% |
| primes | compute | 213.973 | 213.969 | +0.0% | 214.359 | 214.349 | 0.09% | 0.08% |
| ackermann | compute | 63.774 | 63.791 | -0.0% | 63.994 | 63.985 | 0.14% | 0.14% |
| mandelbrot | compute | 113.887 | 113.862 | +0.0% | 114.111 | 114.112 | 0.11% | 0.13% |
| hash | compute | 213.915 | 163.832 | +23.4% | 214.119 | 164.012 | 0.06% | 5.95% |
| alias | alias | 213.897 | 213.910 | -0.0% | 214.207 | 214.201 | 0.05% | 0.06% |
| alloc_churn | allocation | 15.465 | 7.403 | +52.1% | 15.525 | 7.461 | 0.30% | 0.55% |
| string_concat | strings | 31.565 | 15.503 | +50.9% | 31.724 | 15.605 | 0.38% | 0.69% |
| fib | calls | 364.198 | 364.174 | +0.0% | 364.541 | 364.555 | 0.05% | 0.05% |
| collatz | integer | 264.110 | 264.124 | -0.0% | 264.536 | 264.563 | 0.10% | 0.10% |
| gcd_sum | integer | 364.489 | 364.449 | +0.0% | 365.035 | 364.976 | 0.08% | 0.07% |
| primes | integer | 214.242 | 214.249 | -0.0% | 214.663 | 214.658 | 0.08% | 0.08% |
| digit_sum | integer | 364.402 | 364.446 | -0.0% | 365.096 | 365.085 | 0.09% | 0.09% |
| monte_carlo | float | 314.421 | 314.426 | -0.0% | 314.895 | 314.905 | 0.09% | 0.10% |
| concat_chain | memory | 164.173 | 114.196 | +30.4% | 164.516 | 114.421 | 4.27% | 0.11% |
| ptr_accum | pointers | 214.202 | 264.197 | -23.3% | 214.579 | 264.684 | 0.07% | 0.06% |

`best` is the fastest timed run and is the headline comparison. `median` and RSD show how stable the sample is.

## Method

- C flags: `-O3 -march=native -flto -funroll-loops -mtune=native -std=c17 -fno-builtin`.
- Z pipeline: `opt -O3 -mcpu=native; clang -O3 -flto -march=native -funroll-loops -mtune=native`.
- Warmup runs per binary: 2.
- Timed runs per binary: 100.
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
| Load average | [0.93, 0.92, 0.6] |
| Clang | Ubuntu clang version 22.1.2 (1ubuntu1) |
| opt | Ubuntu LLVM version 22.1.2 |
| Java | openjdk version "21.0.13" 2026-10-20 LTS |
| Working tree | clean |

## Reproduce

```sh
python3 benchmarks/bench.py --runs 100 --warmup 2
```
